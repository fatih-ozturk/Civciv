/*
 * Copyright 2024 Fatih OZTURK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.civciv.app.commonui.ext

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Small phone",
    device = "spec:id=reference_phone,shape=Normal,width=360,height=640,unit=dp,dpi=160",
)
@Preview(
    name = "Phone",
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
)
@Preview(
    name = "Foldable",
    device = "spec:id=reference_foldable,shape=Normal,width=673,height=841,unit=dp,dpi=420",
)
@Preview(
    name = "Tablet",
    device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240",
)
@Preview(
    name = "Small phone",
    device = "spec:id=reference_phone,shape=Normal,width=360,height=640,unit=dp,dpi=160",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Phone",
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Foldable",
    device = "spec:id=reference_foldable,shape=Normal,width=673,height=841,unit=dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Tablet",
    device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class CivcivPreview
