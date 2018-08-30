package io.cryptolens.licensing;

import java.util.Date;

/**
 * Information about the customer that the license key belongs to.
 * It is optional for a license key to be associated with a customer.
 */
public class Customer {
    public int Id;
    public String Name;
    public String Email;
    public String CompanyName;
    public Date Created;
}
