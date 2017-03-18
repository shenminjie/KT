/*
 *  Copyright 2016 Jeroen Mols
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.monkeyshen.recordlib.camera;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Jeroen Mols on 06/12/15.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class CameraSizeTest {

    @Test
    public void canInstantiate() throws Exception {
        new CameraSize(0, 0);
    }

    @Test
    public void returnWidth() throws Exception {
        CameraSize cameraSize = new CameraSize(800, 600);

        assertEquals(800, cameraSize.getWidth());
    }

    @Test
    public void returnHeight() throws Exception {
        CameraSize cameraSize = new CameraSize(800, 600);

        assertEquals(600, cameraSize.getHeight());
    }
}