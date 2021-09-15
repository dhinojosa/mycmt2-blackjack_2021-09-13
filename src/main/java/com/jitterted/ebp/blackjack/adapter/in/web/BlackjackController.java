package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.function.Supplier;

@Controller
public class BlackjackController {

    private final Supplier<Game> gameSupplier;
    private Game currentGame;

    //interface GameService {
    //   public UUID getNewGame();
    //   public Game loadGame(UUID);
    //
    //}
    public BlackjackController(Supplier<Game> gameSupplier) {
        this.gameSupplier = gameSupplier;
    }

    @PostMapping("/start-game")
    public String startGame() {
        this.currentGame = gameSupplier.get();
        currentGame.initialDeal();
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String getGame(Model model) {
        model.addAttribute("gameView", GameView.of(currentGame));
        return "blackjack";
    }
}
