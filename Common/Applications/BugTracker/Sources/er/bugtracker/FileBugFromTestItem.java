/*
 * Copyright (C) NetStruxr, Inc. All rights reserved.
 *
 * This software is published under the terms of the NetStruxr
 * Public Software License version 0.5, a copy of which has been
 * included with this distribution in the LICENSE.NPL file.  */

package er.bugtracker;
import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import com.webobjects.directtoweb.*;
import er.extensions.*;

public class FileBugFromTestItem extends WOComponent {

    public FileBugFromTestItem(WOContext aContext) {
        super(aContext);
    }

    public TestItem object;
    public String key;
    public Object extraBindings;

    public WOComponent fileBug() {
        EOEditingContext peer = ERXEC.newEditingContext(object.editingContext().parentObjectStore());
        EditPageInterface epi = null;
        peer.lock();
        try {
            TestItem testItem = (TestItem)EOUtilities.localInstanceOfObject(peer,object);
            People user = (People)EOUtilities.localInstanceOfObject(peer,((Session)session()).getUser());
            Component component = (Component)valueForKey("component");

            Bug bug = new Bug();
            peer.insertObject(bug);
            testItem.setState(TestItemState.BUG);

            bug.setTextDescription("[From Test #" + testItem.primaryKey()+"]");
            bug.addToBothSidesOfTestItems(testItem);
            bug.addToBothSidesOfOriginator(user);
            bug.addToBothSidesOfComponent(component);

            epi=(EditPageInterface)D2W.factory().pageForConfigurationNamed("EditNewBug",session());
            epi.setObject(bug);
            epi.setNextPage(context().page());
        } finally {
            peer.unlock();
        }
         return (WOComponent)epi;        
    }
    
    
}