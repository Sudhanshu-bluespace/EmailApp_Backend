/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import com.bluespacetech.core.constants.Labeled;

/**
 * The Enum PageLinkTypeConstant.
 */
public enum PageLinkTypeConstant implements Labeled
{

    /** The link. */
    LINK("link"),
    /** The toggle. */
    TOGGLE("toggle");

    /** The label. */
    private String label;

    /**
     * Instantiates a new page link type constant.
     *
     * @param label the label
     */
    PageLinkTypeConstant(final String label)
    {
        this.label = label;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.constants.Labeled#getLabel()
     */
    @Override
    public String getLabel()
    {
        return label;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.constants.Labeled#setLabel(java.lang.String)
     */
    @Override
    public void setLabel(final String label)
    {
        this.label = label;

    }

}
