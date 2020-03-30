package com.smotteh.milestone6;/*
 * The purpose of this interface is to have the readAllContacts and saveAllContacts methods.
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataAccessService { //start of interface.

    //these methods are beyond the scope of this milestone and will be later implemented.
    void readAllPersonContacts() throws FileNotFoundException;

    void saveAllPersonContacts() throws IOException;

    void readAllBusinessContacts() throws FileNotFoundException;

    void saveAllBusinessContacts() throws IOException;

} //end of interface.
