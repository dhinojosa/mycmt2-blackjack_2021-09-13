package com.jitterted.ebp.blackjack.adapter.out.gamemonitor;

import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.springframework.web.client.RestTemplate;

public class HttpGameMonitor implements GameMonitor{
    @Override
    public void roundCompleted(Game game) {
        try {
            post("https://blackjack-game-monitor.herokuapp.com/api/gameresults", GameResultDTO.of(game));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post(String uri, GameResultDTO gameResultDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, gameResultDto, GameResultDTO.class);
    }
}
