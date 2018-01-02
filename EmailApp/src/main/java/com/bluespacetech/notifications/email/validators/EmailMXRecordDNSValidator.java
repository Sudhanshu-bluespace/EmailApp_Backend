package com.bluespacetech.notifications.email.validators;

import com.bluespacetech.common.util.ExceptionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;

/**
 * The Class EmailMXRecordDNSValidator.
 */
public class EmailMXRecordDNSValidator
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailMXRecordDNSValidator.class);

    /** The init connection timeout. */
    private static boolean initConnectionTimeout = false;

    /**
     * Validate mx record.
     *
     * @param email the email
     * @return the string
     */
    public static String validateMxRecord(String email)
    {
        String[] split = email.split("@");
        String hostname = null;
        if (split.length == 1)
        {
            hostname = email;
        }
        else
        {
            hostname = split[1];
        }
        String res = null;
        MXRecord mxRecord = null;
        String[] response = null;
        try
        {
            mxRecord = digOptimalMxRecords(hostname);
            if (mxRecord == null)
            {
                if (initConnectionTimeout)
                {
                    response = lookupMailHosts(hostname);
                    if (response != null)
                    {
                        StringBuilder sb = new StringBuilder();
                        sb.append("\"Response : {\"");
                        for (String r : response)
                        {
                            sb.append("\"host:\"" + r + "\"");
                        }
                        sb.append("}\"");
                        res = sb.toString();
                    }
                }
            }
            else
            {
                StringBuilder sb = new StringBuilder();
                sb.append("\"Response : {\"");
                sb.append("\"dClass:\"" + mxRecord.getDClass() + "\",");
                sb.append("\"priority:\"" + mxRecord.getPriority() + "\",");
                sb.append("\"RrsetType:\"" + mxRecord.getRRsetType() + "\",");
                sb.append("\"TTL:\"" + mxRecord.getTTL() + "\",");
                sb.append("\"Type:\"" + mxRecord.getType() + "\"");
                sb.append("}\"");
                res = sb.toString();
            }
        }
        catch (TextParseException | NamingException e)
        {
            LOGGER.error("Failed to scan MX records, reason : " + ExceptionUtil.getErrorRootCause(e));
        }
        initConnectionTimeout = false;
        return res;
    }

    /**
     * Dig optimal mx records.
     *
     * @param domainName the domain name
     * @return the MX record
     * @throws TextParseException the text parse exception
     */
    public static MXRecord digOptimalMxRecords(String domainName) throws TextParseException
    {
        List<MXRecord> records = digMxRecords(domainName);
        if (!records.isEmpty())
        {
            /*Collections.sort(records, new Comparator<MXRecord>() {

                @Override
                public int compare(MXRecord o1, MXRecord o2)
                {
                    // TODO Auto-generated method stub
                    return o1.compareTo(o2);
                }
            });*/

            return records.get(0);
        }
        return null;
    }

    /**
     * Dig mx records.
     *
     * @param domainName the domain name
     * @return the list
     * @throws TextParseException the text parse exception
     */
    public static List<MXRecord> digMxRecords(String domainName) throws TextParseException
    {
        LOGGER.info("Validating mX recors for domain : " + domainName);
        List<MXRecord> list = new ArrayList<>();
        Lookup mxLookupper = new Lookup(domainName, 15);
        mxLookupper.run();
        if (mxLookupper.getResult() == 0)
        {
            Record[] answers = mxLookupper.getAnswers();
            for (Record record : answers)
            {
                list.add((MXRecord) record);
            }
        }
        else
        {
            switch (mxLookupper.getResult())
            {
            case 3:
                LOGGER.error("MX Record Validation Failed - [HOST_NOT_FOUND]");
                initConnectionTimeout = false;
                break;
            case 4:
                LOGGER.error("MX Record Validation Failed - [TYPE_NOT_FOUND]");
                initConnectionTimeout = false;
                break;
            case 2:
                LOGGER.error("MX Record Initial Validation Failed due to time out - [TRY_AGAIN]");
                initConnectionTimeout = true;
                break;
            case 1:
                LOGGER.error("MX Record Validation Failed - [UNRECOVERABLE]");
                initConnectionTimeout = false;
            }
        }
        return list;
    }

    /**
     * Lookup mail hosts.
     *
     * @param domainName the domain name
     * @return the string[]
     * @throws NamingException the naming exception
     */
    private static String[] lookupMailHosts(String domainName) throws NamingException
    {
        InitialDirContext iDirC = new InitialDirContext();

        Attributes attributes = iDirC.getAttributes("dns:/" + domainName, new String[] { "MX" });

        Attribute attributeMX = attributes.get("MX");
        if (attributeMX == null)
        {
            return new String[] { domainName };
        }
        String[][] pvhn = new String[attributeMX.size()][2];
        for (int i = 0; i < attributeMX.size(); i++)
        {
            pvhn[i] = ("" + attributeMX.get(i)).split("\\s+");
        }

        Arrays.sort(pvhn, new Comparator<String[]>() {
            public int compare(String[] o1, String[] o2)
            {
                return (Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
            }
        });

        String[] sortedHostNames = new String[pvhn.length];
        for (int i = 0; i < pvhn.length; i++)
        {
            sortedHostNames[i] = (pvhn[i][1].endsWith(".") ? pvhn[i][1].substring(0, pvhn[i][1].length() - 1)
                    : pvhn[i][1]);
        }
        return sortedHostNames;
    }
}
