//
// Created by sheng on 11/3/2021.
//

#ifndef BLACKMAMBA_DIFFICULTYMENU_HPP
#define BLACKMAMBA_DIFFICULTYMENU_HPP

#pragma once

#include <memory>

#include  <SFML/Graphics/Text.hpp>
#include <SFML/Graphics/Sprite.hpp>
#include "State.hpp"
#include "Game.hpp"


namespace Engine {
    class DifficultyMenu : public Engine::State {
    private:
        std::shared_ptr<Context> context;
        sf::Text gameTitle;
        sf::Sprite snake;
        sf::Sprite cobra1;
        sf::Sprite cobra2;
        sf::Sprite easyButton;
        sf::Sprite hardButton;
        sf::Sprite backButton;
        bool normalPressed;
        bool hardPressed;
        bool backPressed;


    public:
        DifficultyMenu(std::shared_ptr<Context> &context);

        void init() override;

        void processInput() override;

        void update(sf::Time) override;

        void draw() override;
    };
}

#endif