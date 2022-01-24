//
// Created by sheng on 11/3/2021.
//

#ifndef BLACKMAMBA_PAUSE_HPP
#define BLACKMAMBA_PAUSE_HPP

#pragma once

#include <memory>
#include  <SFML/Graphics/Text.hpp>
#include <SFML/Graphics/Sprite.hpp>
#include "State.hpp"
#include "Game.hpp"


namespace Engine {
    class Pause : public Engine::State {
    private:
        std::shared_ptr<Context> context;
        sf::Text gameTitle;
        sf::Sprite pause;
        sf::Sprite rectangle;
        sf::Sprite exitButton;
        bool normalPressed;
        bool exitPressed;
        int difficulty;


    public:
        Pause(std::shared_ptr<Context> &context, int = HARD);

        ~Pause();

        void init() override;

        void processInput() override;

        void update(sf::Time) override;

        void draw() override;
    };
}

#endif