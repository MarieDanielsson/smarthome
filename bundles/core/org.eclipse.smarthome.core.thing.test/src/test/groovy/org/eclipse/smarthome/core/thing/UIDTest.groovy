/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test

class UIDTest {

    @Test(expected=IllegalArgumentException)
    void 'UID cannot be constructed with invalid charaters'() {
        new ThingUID("binding:type:id_with_invalidchar#")
    }

    @Test
    void 'valid UIDs'() {
        new ThingUID("binding:type:id-1")
        new ThingUID("binding:type:id_1")
        new ThingUID("binding:type:ID")
        new ThingUID("00:type:ID")
    }
}
