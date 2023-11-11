/*
 * Copyright 2023 Fatih OZTURK
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
package com.civciv.app.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

object CivcivPalette {
    val brand50: Color = Color(0xFFF5F4FF)
    val brand100: Color = Color(0xFFE7E6FF)
    val brand200: Color = Color(0xFFD2D1FF)
    val brand300: Color = Color(0xFFB7B6FF)
    val brand400: Color = Color(0xFFA3A1FF)
    val brand500: Color = Color(0xFF8C89FF)
    val brand600: Color = Color(0xFF746FFF)
    val brand700: Color = Color(0xFF5A55FF)
    val brand800: Color = Color(0xFF423CFF)
    val brand900: Color = Color(0xFF302AFF)
    val error50: Color = Color(0xFFFEF3F2)
    val error100: Color = Color(0xFFFEE4E2)
    val error200: Color = Color(0xFFFECDCA)
    val error300: Color = Color(0xFFFDA29B)
    val error400: Color = Color(0xFFF97066)
    val error500: Color = Color(0xFFF04438)
    val error600: Color = Color(0xFFD92D20)
    val error700: Color = Color(0xFFB42318)
    val error800: Color = Color(0xFF912018)
    val error950: Color = Color(0xFF55160C)
    val gray25: Color = Color(0xFFFCFCFD)
    val gray50: Color = Color(0xFFF8FAFC)
    val gray100: Color = Color(0xFFEEF2F6)
    val gray200: Color = Color(0xFFE3E8EF)
    val gray300: Color = Color(0xFFCDD5DF)
    val gray400: Color = Color(0xFF9AA4B2)
    val gray500: Color = Color(0xFF697586)
    val gray600: Color = Color(0xFF4B5565)
    val gray700: Color = Color(0xFF364152)
    val gray800: Color = Color(0xFF202939)
    val gray900: Color = Color(0xFF121926)
    val gray950: Color = Color(0xFF0D121C)
    val success50: Color = Color(0xFFECFDF3)
    val success100: Color = Color(0xFFDCFAE6)
    val success400: Color = Color(0xFF47CD89)
    val success500: Color = Color(0xFF17B26A)
    val success600: Color = Color(0xFF079455)
    val textWhite: Color = Color(0xFFF7F7F7)
    val warning50: Color = Color(0xFFFFFAEB)
    val warning100: Color = Color(0xFFFEF0C7)
    val warning400: Color = Color(0xFFFDB022)
    val warning500: Color = Color(0xFFF79009)
    val warning600: Color = Color(0xFFDC6803)
    val white: Color = Color(0xFFFAF4F4)
    val black: Color = Color(0xFF000000)
}

@Stable
class CivcivColors(
    textPrimary: Color,
    textPrimaryOnBrand: Color,
    textSecondary: Color,
    textSecondaryHover: Color,
    textSecondaryOnBrand: Color,
    textTertiary: Color,
    textTertiaryHover: Color,
    textTertiaryOnBrand: Color,
    textQuarterary: Color,
    textQuarteraryOnBrand: Color,
    textWhite: Color,
    textDisabled: Color,
    textPlaceholder: Color,
    textPlaceholderSubtle: Color,
    textBrandPrimary: Color,
    textBrandSecondary: Color,
    textBrandTertiary: Color,
    textBrandTertiaryAlt: Color,
    textErrorPrimary: Color,
    textWarningPrimary: Color,
    textSuccessPrimary: Color,
    bgPrimary: Color,
    bgPrimaryAlt: Color,
    bgPrimaryHover: Color,
    bgPrimarySolid: Color,
    bgSecondary: Color,
    bgSecondaryAlt: Color,
    bgSecondaryHover: Color,
    bgSecondarySubtle: Color,
    bgSecondarySolid: Color,
    bgTertiary: Color,
    bgQuarterary: Color,
    bgActive: Color,
    bgDisabled: Color,
    bgDisabledSubtle: Color,
    bgOverlay: Color,
    bgBrandPrimary: Color,
    bgBrandPrimaryAlt: Color,
    bgBrandSecondary: Color,
    bgBrandSolid: Color,
    bgBrandSolidHover: Color,
    bgBrandSection: Color,
    bgBrandSectionSubtle: Color,
    bgErrorPrimary: Color,
    bgErrorSecondary: Color,
    bgErrorSolid: Color,
    bgWarningPrimary: Color,
    bgWarningSecondary: Color,
    bgWarningSolid: Color,
    bgSuccessPrimary: Color,
    bgSuccessSecondary: Color,
    bgSuccessSolid: Color,
    fgPrimary: Color,
    fgSecondary: Color,
    fgSecondaryHover: Color,
    fgTertiary: Color,
    fgTertiaryHover: Color,
    fgQuarterary: Color,
    fgQuarteraryHover: Color,
    fgQuinary: Color,
    fgQuinaryHover: Color,
    fgSenary: Color,
    fgWhite: Color,
    fgDisabled: Color,
    fgDisabledSubtle: Color,
    fgBrandPrimary: Color,
    fgBrandPrimaryAlt: Color,
    fgBrandSecondary: Color,
    fgErrorPrimary: Color,
    fgErrorSecondary: Color,
    fgWarningPrimary: Color,
    fgWarningSecondary: Color,
    fgSuccessPrimary: Color,
    fgSuccessSecondary: Color,
    borderPrimary: Color,
    borderSecondary: Color,
    borderTertiary: Color,
    borderDisabled: Color,
    borderDisabledSubtle: Color,
    borderBrand: Color,
    borderBrandSolid: Color,
    borderBrandSolidAlt: Color,
    borderError: Color,
    borderErrorSolid: Color,
    buttonPrimaryBg: Color,
    buttonPrimaryFg: Color,
    buttonSecondaryBg: Color,
    buttonSecondaryFg: Color,
    buttonSecondaryBorder: Color,
    buttonSecondaryColorBg: Color,
    buttonSecondaryColorFg: Color,
    buttonSecondaryColorBorder: Color,
    buttonTertiaryFg: Color,
    buttonTertiaryColorFg: Color,
    buttonPrimaryErrorBg: Color,
    buttonSecondaryErrorBg: Color,
    buttonSecondaryErrorFg: Color,
    buttonSecondaryErrorBorder: Color,
    buttonTertiaryErrorFg: Color,
) {
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textPrimaryOnBrand by mutableStateOf(textPrimaryOnBrand)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textSecondaryHover by mutableStateOf(textSecondaryHover)
        private set
    var textSecondaryOnBrand by mutableStateOf(textSecondaryOnBrand)
        private set
    var textTertiary by mutableStateOf(textTertiary)
        private set
    var textTertiaryHover by mutableStateOf(textTertiaryHover)
        private set
    var textTertiaryOnBrand by mutableStateOf(textTertiaryOnBrand)
        private set
    var textQuarterary by mutableStateOf(textQuarterary)
        private set
    var textQuarteraryOnBrand by mutableStateOf(textQuarteraryOnBrand)
        private set
    var textWhite by mutableStateOf(textWhite)
        private set
    var textDisabled by mutableStateOf(textDisabled)
        private set
    var textPlaceholder by mutableStateOf(textPlaceholder)
        private set
    var textPlaceholderSubtle by mutableStateOf(textPlaceholderSubtle)
        private set
    var textBrandPrimary by mutableStateOf(textBrandPrimary)
        private set
    var textBrandSecondary by mutableStateOf(textBrandSecondary)
        private set
    var textBrandTertiary by mutableStateOf(textBrandTertiary)
        private set
    var textBrandTertiaryAlt by mutableStateOf(textBrandTertiaryAlt)
        private set
    var textErrorPrimary by mutableStateOf(textErrorPrimary)
        private set
    var textWarningPrimary by mutableStateOf(textWarningPrimary)
        private set
    var textSuccessPrimary by mutableStateOf(textSuccessPrimary)
        private set
    var bgPrimary by mutableStateOf(bgPrimary)
        private set
    var bgPrimaryAlt by mutableStateOf(bgPrimaryAlt)
        private set
    var bgPrimaryHover by mutableStateOf(bgPrimaryHover)
        private set
    var bgPrimarySolid by mutableStateOf(bgPrimarySolid)
        private set
    var bgSecondary by mutableStateOf(bgSecondary)
        private set
    var bgSecondaryAlt by mutableStateOf(bgSecondaryAlt)
        private set
    var bgSecondaryHover by mutableStateOf(bgSecondaryHover)
        private set
    var bgSecondarySubtle by mutableStateOf(bgSecondarySubtle)
        private set
    var bgSecondarySolid by mutableStateOf(bgSecondarySolid)
        private set
    var bgTertiary by mutableStateOf(bgTertiary)
        private set
    var bgQuarterary by mutableStateOf(bgQuarterary)
        private set
    var bgActive by mutableStateOf(bgActive)
        private set
    var bgDisabled by mutableStateOf(bgDisabled)
        private set
    var bgDisabledSubtle by mutableStateOf(bgDisabledSubtle)
        private set
    var bgOverlay by mutableStateOf(bgOverlay)
        private set
    var bgBrandPrimary by mutableStateOf(bgBrandPrimary)
        private set
    var bgBrandPrimaryAlt by mutableStateOf(bgBrandPrimaryAlt)
        private set
    var bgBrandSecondary by mutableStateOf(bgBrandSecondary)
        private set
    var bgBrandSolid by mutableStateOf(bgBrandSolid)
        private set
    var bgBrandSolidHover by mutableStateOf(bgBrandSolidHover)
        private set
    var bgBrandSection by mutableStateOf(bgBrandSection)
        private set
    var bgBrandSectionSubtle by mutableStateOf(bgBrandSectionSubtle)
        private set
    var bgErrorPrimary by mutableStateOf(bgErrorPrimary)
        private set
    var bgErrorSecondary by mutableStateOf(bgErrorSecondary)
        private set
    var bgErrorSolid by mutableStateOf(bgErrorSolid)
        private set
    var bgWarningPrimary by mutableStateOf(bgWarningPrimary)
        private set
    var bgWarningSecondary by mutableStateOf(bgWarningSecondary)
        private set
    var bgWarningSolid by mutableStateOf(bgWarningSolid)
        private set
    var bgSuccessPrimary by mutableStateOf(bgSuccessPrimary)
        private set
    var bgSuccessSecondary by mutableStateOf(bgSuccessSecondary)
        private set
    var bgSuccessSolid by mutableStateOf(bgSuccessSolid)
        private set
    var fgPrimary by mutableStateOf(fgPrimary)
        private set
    var fgSecondary by mutableStateOf(fgSecondary)
        private set
    var fgSecondaryHover by mutableStateOf(fgSecondaryHover)
        private set
    var fgTertiary by mutableStateOf(fgTertiary)
        private set
    var fgTertiaryHover by mutableStateOf(fgTertiaryHover)
        private set
    var fgQuarterary by mutableStateOf(fgQuarterary)
        private set
    var fgQuarteraryHover by mutableStateOf(fgQuarteraryHover)
        private set
    var fgQuinary by mutableStateOf(fgQuinary)
        private set
    var fgQuinaryHover by mutableStateOf(fgQuinaryHover)
        private set
    var fgSenary by mutableStateOf(fgSenary)
        private set
    var fgWhite by mutableStateOf(fgWhite)
        private set
    var fgDisabled by mutableStateOf(fgDisabled)
        private set
    var fgDisabledSubtle by mutableStateOf(fgDisabledSubtle)
        private set
    var fgBrandPrimary by mutableStateOf(fgBrandPrimary)
        private set
    var fgBrandPrimaryAlt by mutableStateOf(fgBrandPrimaryAlt)
        private set
    var fgBrandSecondary by mutableStateOf(fgBrandSecondary)
        private set
    var fgErrorPrimary by mutableStateOf(fgErrorPrimary)
        private set
    var fgErrorSecondary by mutableStateOf(fgErrorSecondary)
        private set
    var fgWarningPrimary by mutableStateOf(fgWarningPrimary)
        private set
    var fgWarningSecondary by mutableStateOf(fgWarningSecondary)
        private set
    var fgSuccessPrimary by mutableStateOf(fgSuccessPrimary)
        private set
    var fgSuccessSecondary by mutableStateOf(fgSuccessSecondary)
        private set
    var borderPrimary by mutableStateOf(borderPrimary)
        private set
    var borderSecondary by mutableStateOf(borderSecondary)
        private set
    var borderTertiary by mutableStateOf(borderTertiary)
        private set
    var borderDisabled by mutableStateOf(borderDisabled)
        private set
    var borderDisabledSubtle by mutableStateOf(borderDisabledSubtle)
        private set
    var borderBrand by mutableStateOf(borderBrand)
        private set
    var borderBrandSolid by mutableStateOf(borderBrandSolid)
        private set
    var borderBrandSolidAlt by mutableStateOf(borderBrandSolidAlt)
        private set
    var borderError by mutableStateOf(borderError)
        private set
    var borderErrorSolid by mutableStateOf(borderErrorSolid)
        private set
    var buttonPrimaryBg by mutableStateOf(buttonPrimaryBg)
        private set
    var buttonPrimaryFg by mutableStateOf(buttonPrimaryFg)
        private set
    var buttonSecondaryBg by mutableStateOf(buttonSecondaryBg)
        private set
    var buttonSecondaryFg by mutableStateOf(buttonSecondaryFg)
        private set
    var buttonSecondaryBorder by mutableStateOf(buttonSecondaryBorder)
        private set
    var buttonSecondaryColorBg by mutableStateOf(buttonSecondaryColorBg)
        private set
    var buttonSecondaryColorFg by mutableStateOf(buttonSecondaryColorFg)
        private set
    var buttonSecondaryColorBorder by mutableStateOf(buttonSecondaryColorBorder)
        private set
    var buttonTertiaryFg by mutableStateOf(buttonTertiaryFg)
        private set
    var buttonTertiaryColorFg by mutableStateOf(buttonTertiaryColorFg)
        private set
    var buttonPrimaryErrorBg by mutableStateOf(buttonPrimaryErrorBg)
        private set
    var buttonSecondaryErrorBg by mutableStateOf(buttonSecondaryErrorBg)
        private set
    var buttonSecondaryErrorFg by mutableStateOf(buttonSecondaryErrorFg)
        private set
    var buttonSecondaryErrorBorder by mutableStateOf(buttonSecondaryErrorBorder)
        private set
    var buttonTertiaryErrorFg by mutableStateOf(buttonTertiaryErrorFg)
        private set

    fun update(other: CivcivColors) {
        textPrimary = other.textPrimary
        textPrimaryOnBrand = other.textPrimaryOnBrand
        textSecondary = other.textSecondary
        textSecondaryHover = other.textSecondaryHover
        textSecondaryOnBrand = other.textSecondaryOnBrand
        textTertiary = other.textTertiary
        textTertiaryHover = other.textTertiaryHover
        textTertiaryOnBrand = other.textTertiaryOnBrand
        textQuarterary = other.textQuarterary
        textQuarteraryOnBrand = other.textQuarteraryOnBrand
        textWhite = other.textWhite
        textDisabled = other.textDisabled
        textPlaceholder = other.textPlaceholder
        textPlaceholderSubtle = other.textPlaceholderSubtle
        textBrandPrimary = other.textBrandPrimary
        textBrandSecondary = other.textBrandSecondary
        textBrandTertiary = other.textBrandTertiary
        textBrandTertiaryAlt = other.textBrandTertiaryAlt
        textErrorPrimary = other.textErrorPrimary
        textWarningPrimary = other.textWarningPrimary
        textSuccessPrimary = other.textSuccessPrimary
        bgPrimary = other.bgPrimary
        bgPrimaryAlt = other.bgPrimaryAlt
        bgPrimaryHover = other.bgPrimaryHover
        bgPrimarySolid = other.bgPrimarySolid
        bgSecondary = other.bgSecondary
        bgSecondaryAlt = other.bgSecondaryAlt
        bgSecondaryHover = other.bgSecondaryHover
        bgSecondarySubtle = other.bgSecondarySubtle
        bgSecondarySolid = other.bgSecondarySolid
        bgTertiary = other.bgTertiary
        bgQuarterary = other.bgQuarterary
        bgActive = other.bgActive
        bgErrorPrimary = other.bgErrorPrimary
        bgErrorSecondary = other.bgErrorSecondary
        bgErrorSolid = other.bgErrorSolid
        bgWarningPrimary = other.bgWarningPrimary
        bgWarningSecondary = other.bgWarningSecondary
        bgWarningSolid = other.bgWarningSolid
        bgSuccessPrimary = other.bgSuccessPrimary
        bgSuccessSecondary = other.bgSuccessSecondary
        bgSuccessSolid = other.bgSuccessSolid
        fgPrimary = other.fgPrimary
        fgSecondary = other.fgSecondary
        fgSecondaryHover = other.fgSecondaryHover
        fgTertiary = other.fgTertiary
        fgTertiaryHover = other.fgTertiaryHover
        fgQuarterary = other.fgQuarterary
        fgQuarteraryHover = other.fgQuarteraryHover
        fgQuinary = other.fgQuinary
        fgQuinaryHover = other.fgQuinaryHover
        fgSenary = other.fgSenary
        fgWhite = other.fgWhite
        fgDisabled = other.fgDisabled
        fgDisabledSubtle = other.fgDisabledSubtle
        fgBrandPrimary = other.fgBrandPrimary
        fgBrandPrimaryAlt = other.fgBrandPrimaryAlt
        fgBrandSecondary = other.fgBrandSecondary
        fgErrorPrimary = other.fgErrorPrimary
        fgErrorSecondary = other.fgErrorSecondary
        fgWarningPrimary = other.fgWarningPrimary
        fgWarningSecondary = other.fgWarningSecondary
        fgSuccessPrimary = other.fgSuccessPrimary
        fgSuccessSecondary = other.fgSuccessSecondary
        borderPrimary = other.borderPrimary
        borderSecondary = other.borderSecondary
        borderTertiary = other.borderTertiary
        borderDisabled = other.borderDisabled
        borderDisabledSubtle = other.borderDisabledSubtle
        borderBrand = other.borderBrand
        borderBrandSolid = other.borderBrandSolid
        borderBrandSolidAlt = other.borderBrandSolidAlt
        borderError = other.borderError
        borderErrorSolid = other.borderErrorSolid
        buttonPrimaryBg = other.buttonPrimaryBg
        buttonPrimaryFg = other.buttonPrimaryFg
        buttonSecondaryBg = other.buttonSecondaryBg
        buttonSecondaryFg = other.buttonSecondaryFg
        buttonSecondaryBorder = other.buttonSecondaryBorder
        buttonSecondaryColorBg = other.buttonSecondaryColorBg
        buttonSecondaryColorFg = other.buttonSecondaryColorFg
        buttonSecondaryColorBorder = other.buttonSecondaryColorBorder
        buttonTertiaryFg = other.buttonTertiaryFg
        buttonTertiaryColorFg = other.buttonTertiaryColorFg
        buttonPrimaryErrorBg = other.buttonPrimaryErrorBg
        buttonSecondaryErrorBg = other.buttonSecondaryErrorBg
        buttonSecondaryErrorFg = other.buttonSecondaryErrorFg
        buttonSecondaryErrorBorder = other.buttonSecondaryErrorBorder
        buttonTertiaryErrorFg = other.buttonTertiaryErrorFg
    }
}

val lightColors = CivcivColors(
    textPrimary = CivcivPalette.gray900,
    textPrimaryOnBrand = CivcivPalette.white,
    textSecondary = CivcivPalette.gray700,
    textSecondaryHover = CivcivPalette.gray800,
    textSecondaryOnBrand = CivcivPalette.brand200,
    textTertiary = CivcivPalette.gray600,
    textTertiaryHover = CivcivPalette.gray700,
    textTertiaryOnBrand = CivcivPalette.brand200,
    textQuarterary = CivcivPalette.gray500,
    textQuarteraryOnBrand = CivcivPalette.brand300,
    textWhite = CivcivPalette.textWhite,
    textDisabled = CivcivPalette.gray500,
    textPlaceholder = CivcivPalette.gray500,
    textPlaceholderSubtle = CivcivPalette.gray300,
    textBrandPrimary = CivcivPalette.brand900,
    textBrandSecondary = CivcivPalette.brand700,
    textBrandTertiary = CivcivPalette.brand600,
    textBrandTertiaryAlt = CivcivPalette.brand600,
    textErrorPrimary = CivcivPalette.error600,
    textWarningPrimary = CivcivPalette.warning600,
    textSuccessPrimary = CivcivPalette.success600,
    bgPrimary = CivcivPalette.white,
    bgPrimaryAlt = CivcivPalette.white,
    bgPrimaryHover = CivcivPalette.gray50,
    bgPrimarySolid = CivcivPalette.gray950,
    bgSecondary = CivcivPalette.gray50,
    bgSecondaryAlt = CivcivPalette.gray50,
    bgSecondaryHover = CivcivPalette.gray100,
    bgSecondarySubtle = CivcivPalette.gray25,
    bgSecondarySolid = CivcivPalette.gray600,
    bgTertiary = CivcivPalette.gray100,
    bgQuarterary = CivcivPalette.gray200,
    bgActive = CivcivPalette.gray50,
    bgDisabled = CivcivPalette.gray100,
    bgDisabledSubtle = CivcivPalette.gray50,
    bgOverlay = CivcivPalette.gray950,
    bgBrandPrimary = CivcivPalette.brand50,
    bgBrandPrimaryAlt = CivcivPalette.brand50,
    bgBrandSecondary = CivcivPalette.brand100,
    bgBrandSolid = CivcivPalette.brand600,
    bgBrandSolidHover = CivcivPalette.brand700,
    bgBrandSection = CivcivPalette.brand800,
    bgBrandSectionSubtle = CivcivPalette.brand700,
    bgErrorPrimary = CivcivPalette.error50,
    bgErrorSecondary = CivcivPalette.error100,
    bgErrorSolid = CivcivPalette.error600,
    bgWarningPrimary = CivcivPalette.warning50,
    bgWarningSecondary = CivcivPalette.warning100,
    bgWarningSolid = CivcivPalette.warning600,
    bgSuccessPrimary = CivcivPalette.success50,
    bgSuccessSecondary = CivcivPalette.success100,
    bgSuccessSolid = CivcivPalette.success600,
    fgPrimary = CivcivPalette.gray900,
    fgSecondary = CivcivPalette.gray700,
    fgSecondaryHover = CivcivPalette.gray800,
    fgTertiary = CivcivPalette.gray600,
    fgTertiaryHover = CivcivPalette.gray700,
    fgQuarterary = CivcivPalette.gray500,
    fgQuarteraryHover = CivcivPalette.gray600,
    fgQuinary = CivcivPalette.gray400,
    fgQuinaryHover = CivcivPalette.gray500,
    fgSenary = CivcivPalette.gray300,
    fgWhite = CivcivPalette.textWhite,
    fgDisabled = CivcivPalette.gray400,
    fgDisabledSubtle = CivcivPalette.gray300,
    fgBrandPrimary = CivcivPalette.brand600,
    fgBrandPrimaryAlt = CivcivPalette.brand600,
    fgBrandSecondary = CivcivPalette.brand500,
    fgErrorPrimary = CivcivPalette.error600,
    fgErrorSecondary = CivcivPalette.error500,
    fgWarningPrimary = CivcivPalette.warning600,
    fgWarningSecondary = CivcivPalette.warning500,
    fgSuccessPrimary = CivcivPalette.success600,
    fgSuccessSecondary = CivcivPalette.success500,
    borderPrimary = CivcivPalette.gray300,
    borderSecondary = CivcivPalette.gray200,
    borderTertiary = CivcivPalette.gray100,
    borderDisabled = CivcivPalette.gray300,
    borderDisabledSubtle = CivcivPalette.gray200,
    borderBrand = CivcivPalette.brand300,
    borderBrandSolid = CivcivPalette.brand600,
    borderBrandSolidAlt = CivcivPalette.brand600,
    borderError = CivcivPalette.error300,
    borderErrorSolid = CivcivPalette.error600,
    buttonPrimaryBg = CivcivPalette.brand600,
    buttonPrimaryFg = CivcivPalette.white,
    buttonSecondaryBg = CivcivPalette.white,
    buttonSecondaryFg = CivcivPalette.gray700,
    buttonSecondaryBorder = CivcivPalette.gray300,
    buttonSecondaryColorBg = CivcivPalette.white,
    buttonSecondaryColorFg = CivcivPalette.brand700,
    buttonSecondaryColorBorder = CivcivPalette.brand300,
    buttonTertiaryFg = CivcivPalette.gray600,
    buttonTertiaryColorFg = CivcivPalette.brand700,
    buttonPrimaryErrorBg = CivcivPalette.error600,
    buttonSecondaryErrorBg = CivcivPalette.white,
    buttonSecondaryErrorFg = CivcivPalette.error700,
    buttonSecondaryErrorBorder = CivcivPalette.error300,
    buttonTertiaryErrorFg = CivcivPalette.error700,
)

val darkColors = CivcivColors(
    textPrimary = CivcivPalette.gray50,
    textPrimaryOnBrand = CivcivPalette.gray50,
    textSecondary = CivcivPalette.gray300,
    textSecondaryHover = CivcivPalette.gray200,
    textSecondaryOnBrand = CivcivPalette.gray300,
    textTertiary = CivcivPalette.gray400,
    textTertiaryHover = CivcivPalette.gray300,
    textTertiaryOnBrand = CivcivPalette.gray400,
    textQuarterary = CivcivPalette.gray400,
    textQuarteraryOnBrand = CivcivPalette.gray400,
    textWhite = CivcivPalette.textWhite,
    textDisabled = CivcivPalette.gray500,
    textPlaceholder = CivcivPalette.gray400,
    textPlaceholderSubtle = CivcivPalette.gray700,
    textBrandPrimary = CivcivPalette.gray50,
    textBrandSecondary = CivcivPalette.gray300,
    textBrandTertiary = CivcivPalette.gray400,
    textBrandTertiaryAlt = CivcivPalette.gray50,
    textErrorPrimary = CivcivPalette.error400,
    textWarningPrimary = CivcivPalette.warning400,
    textSuccessPrimary = CivcivPalette.success400,
    bgPrimary = CivcivPalette.gray950,
    bgPrimaryAlt = CivcivPalette.gray900,
    bgPrimaryHover = CivcivPalette.gray800,
    bgPrimarySolid = CivcivPalette.gray900,
    bgSecondary = CivcivPalette.gray900,
    bgSecondaryAlt = CivcivPalette.gray950,
    bgSecondaryHover = CivcivPalette.gray800,
    bgSecondarySubtle = CivcivPalette.gray900,
    bgSecondarySolid = CivcivPalette.gray600,
    bgTertiary = CivcivPalette.gray800,
    bgQuarterary = CivcivPalette.gray700,
    bgActive = CivcivPalette.gray800,
    bgDisabled = CivcivPalette.gray800,
    bgDisabledSubtle = CivcivPalette.gray900,
    bgOverlay = CivcivPalette.gray800,
    bgBrandPrimary = CivcivPalette.brand500,
    bgBrandPrimaryAlt = CivcivPalette.gray800,
    bgBrandSecondary = CivcivPalette.brand600,
    bgBrandSolid = CivcivPalette.brand600,
    bgBrandSolidHover = CivcivPalette.brand500,
    bgBrandSection = CivcivPalette.gray800,
    bgBrandSectionSubtle = CivcivPalette.gray950,
    bgErrorPrimary = CivcivPalette.error500,
    bgErrorSecondary = CivcivPalette.error600,
    bgErrorSolid = CivcivPalette.error600,
    bgWarningPrimary = CivcivPalette.warning500,
    bgWarningSecondary = CivcivPalette.warning600,
    bgWarningSolid = CivcivPalette.warning600,
    bgSuccessPrimary = CivcivPalette.success500,
    bgSuccessSecondary = CivcivPalette.success600,
    bgSuccessSolid = CivcivPalette.success600,
    fgPrimary = CivcivPalette.white,
    fgSecondary = CivcivPalette.gray300,
    fgSecondaryHover = CivcivPalette.gray200,
    fgTertiary = CivcivPalette.gray400,
    fgTertiaryHover = CivcivPalette.gray300,
    fgQuarterary = CivcivPalette.gray400,
    fgQuarteraryHover = CivcivPalette.gray300,
    fgQuinary = CivcivPalette.gray500,
    fgQuinaryHover = CivcivPalette.gray400,
    fgSenary = CivcivPalette.gray600,
    fgWhite = CivcivPalette.textWhite,
    fgDisabled = CivcivPalette.gray500,
    fgDisabledSubtle = CivcivPalette.gray600,
    fgBrandPrimary = CivcivPalette.brand500,
    fgBrandPrimaryAlt = CivcivPalette.gray300,
    fgBrandSecondary = CivcivPalette.brand500,
    fgErrorPrimary = CivcivPalette.error500,
    fgErrorSecondary = CivcivPalette.error400,
    fgWarningPrimary = CivcivPalette.warning500,
    fgWarningSecondary = CivcivPalette.warning400,
    fgSuccessPrimary = CivcivPalette.success500,
    fgSuccessSecondary = CivcivPalette.success400,
    borderPrimary = CivcivPalette.gray700,
    borderSecondary = CivcivPalette.gray800,
    borderTertiary = CivcivPalette.gray800,
    borderDisabled = CivcivPalette.gray700,
    borderDisabledSubtle = CivcivPalette.gray800,
    borderBrand = CivcivPalette.brand400,
    borderBrandSolid = CivcivPalette.brand500,
    borderBrandSolidAlt = CivcivPalette.gray700,
    borderError = CivcivPalette.error400,
    borderErrorSolid = CivcivPalette.error500,
    buttonPrimaryBg = CivcivPalette.brand600,
    buttonPrimaryFg = CivcivPalette.white,
    buttonSecondaryBg = CivcivPalette.gray900,
    buttonSecondaryFg = CivcivPalette.gray300,
    buttonSecondaryBorder = CivcivPalette.gray700,
    buttonSecondaryColorBg = CivcivPalette.gray900,
    buttonSecondaryColorFg = CivcivPalette.gray300,
    buttonSecondaryColorBorder = CivcivPalette.gray700,
    buttonTertiaryFg = CivcivPalette.gray300,
    buttonTertiaryColorFg = CivcivPalette.gray300,
    buttonPrimaryErrorBg = CivcivPalette.error600,
    buttonSecondaryErrorBg = CivcivPalette.error950,
    buttonSecondaryErrorFg = CivcivPalette.error200,
    buttonSecondaryErrorBorder = CivcivPalette.error800,
    buttonTertiaryErrorFg = CivcivPalette.error300,
)

fun CivcivColors.asMaterial3Colors() = ColorScheme(
    primary = this.bgBrandSolid,
    onPrimary = this.fgWhite,
    primaryContainer = this.fgWhite,
    onPrimaryContainer = this.bgBrandSolid,
    inversePrimary = this.bgBrandSolid,
    secondary = this.bgSecondarySolid,
    onSecondary = this.fgWhite,
    secondaryContainer = this.fgWhite,
    onSecondaryContainer = this.bgSecondarySolid,
    tertiary = this.bgTertiary,
    onTertiary = this.fgTertiary,
    tertiaryContainer = this.bgTertiary,
    onTertiaryContainer = this.fgTertiary,
    background = this.bgPrimary,
    onBackground = this.bgPrimarySolid,
    surface = this.bgPrimary,
    onSurface = this.bgPrimarySolid,
    surfaceVariant = this.bgSecondary,
    onSurfaceVariant = this.fgSecondary,
    surfaceTint = this.bgSecondarySubtle,
    inverseSurface = this.fgSecondary,
    inverseOnSurface = this.bgSecondary,
    error = this.bgErrorSolid,
    onError = this.bgErrorPrimary,
    errorContainer = this.bgErrorSolid,
    onErrorContainer = this.bgErrorPrimary,
    outline = this.borderPrimary,
    outlineVariant = this.borderSecondary,
    scrim = this.borderBrand,
)

internal val LocalCivcivColors = compositionLocalOf<CivcivColors> {
    error("No Color provided!")
}
