/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing.type;

import java.net.URI;
import java.util.List;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ThingTypeUID;


/**
 * The {@link BridgeType} describes a concrete type of a {@link Bridge}.
 * A {@link BridgeType} inherits a {@link org.eclipse.smarthome.core.thing.type.ThingType}
 * and signals a parent-child relation.
 * <p>
 * This description is used as template definition for the creation of the
 * according concrete {@link Bridge} object.
 * <p>
 * <b>Hint:</b> This class is immutable.
 * 
 * @author Michael Grammling - Initial Contribution
 */
public class BridgeType extends ThingType {

    /**
     * @see BridgeType#BridgeType(ThingTypeUID, List, String, String, List, URI)
     */
    public BridgeType(String bindingId, String thingTypeId, String label)
            throws IllegalArgumentException {

        this(new ThingTypeUID(bindingId, thingTypeId), null, label, null, null, null);
    }

    /**
     * Creates a new instance of this class with the specified parameters.
     * 
     * @param uid the unique identifier which identifies this Bridge type within
     *     the overall system (must neither be null, nor empty)
     * 
     * @param supportedBridgeTypeUIDs the unique identifiers to the bridges this Bridge type
     *     supports (could be null or empty)
     * 
     * @param label the human readable label for the according type
     *     (must neither be null nor empty)
     * 
     * @param description the human readable description for the according type
     *     (could be null or empty)
     * 
     * @param channelDefinitions the channels this Bridge type provides (could be null or empty)
     * 
     * @param configDescriptionURI the link to the concrete ConfigDescription (could be null)
     * 
     * @throws IllegalArgumentException if the UID is null or empty,
     *     or the the meta information is null
     */
    public BridgeType(ThingTypeUID uid, List<String> supportedBridgeTypeUIDs,
            String label, String description, List<ChannelDefinition> channelDefinitions,
            URI configDescriptionURI)
            throws IllegalArgumentException {

        super(uid, supportedBridgeTypeUIDs, label, description,
                channelDefinitions, configDescriptionURI);
    }

}
