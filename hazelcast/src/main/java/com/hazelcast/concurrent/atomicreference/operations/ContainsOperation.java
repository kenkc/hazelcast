/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.concurrent.atomicreference.operations;

import com.hazelcast.concurrent.atomicreference.AtomicReferenceContainer;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;

import java.io.IOException;

import static com.hazelcast.concurrent.atomicreference.AtomicReferenceDataSerializerHook.CONTAINS;

public class ContainsOperation extends AbstractAtomicReferenceOperation {

    private boolean returnValue;
    private Data contains;

    public ContainsOperation() {
    }

    public ContainsOperation(String name, Data contains) {
        super(name);
        this.contains = contains;
    }

    @Override
    public void run() throws Exception {
        AtomicReferenceContainer container = getReferenceContainer();
        returnValue = container.contains(contains);
    }

    @Override
    public Object getResponse() {
        return returnValue;
    }

    @Override
    public int getId() {
        return CONTAINS;
    }

    @Override
    protected void writeInternal(ObjectDataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeData(contains);
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        contains = in.readData();
    }
}
