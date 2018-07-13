package com.gatheringhallstudios.mhworlddatabase.assets

import com.gatheringhallstudios.mhworlddatabase.R
import com.gatheringhallstudios.mhworlddatabase.data.types.ArmorType

private fun <T, K> createRegistry(vararg pairs: Pair<T, K>): (T) -> K? {
    val registry = mapOf(*pairs)
    return { registry[it] }
}

val VectorArmorRegistry = fun(type: ArmorType) = when (type) {
    ArmorType.HEAD -> R.drawable.ic_equipment_head_base
    ArmorType.CHEST -> R.drawable.ic_equipment_chest_base
    ArmorType.ARMS -> R.drawable.ic_equipment_arm_base
    ArmorType.WAIST -> R.drawable.ic_equipment_waist_base
    ArmorType.LEGS -> R.drawable.ic_equipment_leg_base
}

val VectorRegistry = createRegistry(
        "Ammo" to R.drawable.ic_items_ammo_base,
        "Carapace" to R.drawable.ic_items_carapace_base,
        "Meat" to R.drawable.ic_items_meat_base,
        "Jaw" to R.drawable.ic_items_monster_jaw_base,
        "Pellets" to R.drawable.ic_items_pellets_base,
        "Slinger" to R.drawable.ic_items_slinger_base
)

val ColorRegistry = createRegistry(
        "rare1" to R.color.icon_gray,
        "rare2" to R.color.icon_white,
        "rare3" to R.color.icon_lime,
        "rare4" to R.color.icon_green,
        "rare5" to R.color.icon_cyan,
        "rare6" to R.color.icon_blue,
        "rare7" to R.color.icon_violet,
        "rare8" to R.color.icon_orange,

        "White" to R.color.icon_white,
        "Gray" to R.color.icon_gray,
        "Pink" to R.color.icon_pink,
        "Red" to R.color.icon_red,
        "DarkRed" to R.color.icon_dark_red,
        "Orange" to R.color.icon_orange,
        "Beige" to R.color.icon_beige,
        "Gold" to R.color.icon_gold,
        "Yellow" to R.color.icon_yellow,
        "Violet" to R.color.icon_violet,
        "Blue" to R.color.icon_blue,
        "Cyan" to R.color.icon_cyan,
        "Green" to R.color.icon_green,
        "DarkGreen" to R.color.icon_dark_green
)