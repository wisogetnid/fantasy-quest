package org.battleofkingdoms.game.states

import org.battleofkingdoms.game.Game
import org.battleofkingdoms.game.player.Player

class GameWaitingForPlayers(expectedNumberOfPlayers: Int, player: Player) : Game(expectedNumberOfPlayers) {

    val players: MutableSet<Player> = mutableSetOf()

    init {
        players.add(player)
    }

    override fun state() = State.WAITING_FOR_PLAYERS

    override fun players(): Set<Player> = players

    fun join(player: Player): Game {
        players.add(player)
        if (players.count() == numberOfPlayers)
            return this.startGame()

        return this
    }

    private fun startGame(): GameInPlay {
        return GameInPlay(super.id, players.toHashSet())
    }
}