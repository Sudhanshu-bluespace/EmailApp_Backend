package com.bluespacetech.notifications.email.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import com.bluespacetech.common.util.ExceptionUtil;

import static org.xbill.DNS.Lookup.HOST_NOT_FOUND;
import static org.xbill.DNS.Lookup.SUCCESSFUL;
import static org.xbill.DNS.Lookup.TRY_AGAIN;
import static org.xbill.DNS.Lookup.TYPE_NOT_FOUND;
import static org.xbill.DNS.Lookup.UNRECOVERABLE;

/**
 * The Class EmailMXRecordDNSValidator.
 */
public class EmailMXRecordDNSValidator
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailMXRecordDNSValidator.class);
    
    private static boolean initConnectionTimeout = false;
    
    

    /**
     * Validate mx record.
     *
     * @param email the email
     * @return the list
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
        /*
         * try { // print the sorted mail exhchange servers for (String mailHost : lookupMailHosts(hostname)) { LOGGER.debug("Host for " + hostname + " : " + mailHost); list.add(mailHost); } } catch
         * (NamingException e) { LOGGER.error("ERROR: Failed to get DNS records for '" + hostname + "', reason: ["+e.getMessage()+"]"); }
         */

        try
        {
            mxRecord = digOptimalMxRecords(hostname);
            if(mxRecord == null)
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
                sb.append("\"dClass:\""+mxRecord.getDClass()+"\",");
                sb.append("\"priority:\""+mxRecord.getPriority()+"\",");
                sb.append("\"RrsetType:\""+mxRecord.getRRsetType()+"\",");
                sb.append("\"TTL:\""+mxRecord.getTTL()+"\",");
                sb.append("\"Type:\""+mxRecord.getType()+"\"");
                sb.append("}\"");
                res = sb.toString();
            }
        }
        catch (TextParseException | NamingException e)
        {
            LOGGER.error("Failed to scan MX records, reason : "+ExceptionUtil.getErrorRootCause(e));
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
            Collections.sort(records, new Comparator<MXRecord>() {
                @Override
                public int compare(MXRecord o1, MXRecord o2)
                {
                    return o2.getPriority() - o1.getPriority();
                }
            });
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
        LOGGER.info("Validating mX recors for domain : "+domainName);
        List<MXRecord> list = new ArrayList<>();
        Lookup mxLookupper = new Lookup(domainName, Type.MX);
        mxLookupper.run();

        if (mxLookupper.getResult() == SUCCESSFUL)
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
                case HOST_NOT_FOUND:
                {
                    LOGGER.error("MX Record Validation Failed - [HOST_NOT_FOUND]");
                    initConnectionTimeout = false;
                    break;
                }
                case TYPE_NOT_FOUND:
                {
                    LOGGER.error("MX Record Validation Failed - [TYPE_NOT_FOUND]");
                    initConnectionTimeout = false;
                    break;
                }
                case TRY_AGAIN:
                {
                    LOGGER.error("MX Record Initial Validation Failed due to time out - [TRY_AGAIN]");
                    initConnectionTimeout = true;
                    break;
                }
                case UNRECOVERABLE:
                {
                    LOGGER.error("MX Record Validation Failed - [UNRECOVERABLE]");
                    initConnectionTimeout = false;
                    break;
                }                
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
        // see: RFC 974 - Mail routing and the domain system
        // see: RFC 1034 - Domain names - concepts and facilities
        // see: http://java.sun.com/j2se/1.5.0/docs/guide/jndi/jndi-dns.html
        // - DNS Service Provider for the Java Naming Directory Interface (JNDI)

        // get the default initial Directory Context
        InitialDirContext iDirC = new InitialDirContext();
        // get the MX records from the default DNS directory service provider
        // NamingException thrown if no DNS record found for domainName
        Attributes attributes = iDirC.getAttributes("dns:/" + domainName, new String[] { "MX" });
        // attributeMX is an attribute ('list') of the Mail Exchange(MX) Resource Records(RR)
        Attribute attributeMX = attributes.get("MX");

        // if there are no MX RRs then default to domainName (see: RFC 974)
        if (attributeMX == null)
        {
            return (new String[] { domainName });
        }

        // split MX RRs into Preference Values(pvhn[0]) and Host Names(pvhn[1])
        String[][] pvhn = new String[attributeMX.size()][2];
        for (int i = 0; i < attributeMX.size(); i++)
        {
            pvhn[i] = ("" + attributeMX.get(i)).split("\\s+");
        }

        // sort the MX RRs by RR value (lower is preferred)
        Arrays.sort(pvhn, new Comparator<String[]>() {
            public int compare(String[] o1, String[] o2)
            {
                return (Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
            }
        });

        // put sorted host names in an array, get rid of any trailing '.'
        String[] sortedHostNames = new String[pvhn.length];
        for (int i = 0; i < pvhn.length; i++)
        {
            sortedHostNames[i] = pvhn[i][1].endsWith(".") ? pvhn[i][1].substring(0, pvhn[i][1].length() - 1)
                    : pvhn[i][1];
        }
        return sortedHostNames;
    }
}
