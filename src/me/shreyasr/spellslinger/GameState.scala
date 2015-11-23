package me.shreyasr.spellslinger

import me.shreyasr.spellslinger.entity.Entity
import me.shreyasr.spellslinger.world.World

case class GameState(world: World, entities: List[Entity])
