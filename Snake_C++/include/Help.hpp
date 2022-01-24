//
// Created by sheng on 11/3/2021.
//

#ifndef BLACKMAMBA_HELP_HPP
#define BLACKMAMBA_HELP_HPP

#pragma once

#include <memory>

#include  <SFML/Graphics/Text.hpp>
#include <SFML/Graphics/Sprite.hpp>
#include "State.hpp"
#include "Game.hpp"


namespace Engine {
    class Help : public Engine::State {
    private:
        std::shared_ptr<Context> context;
        sf::Text gameTitle;
        sf::Sprite backButton;
        sf::Sprite background;

        bool startButtonPressed;


    public:
        Help(std::shared_ptr<Context> &context);

        ~Help();

        void init() override;

        void processInput() override;

        void update(sf::Time) override;

        void draw() override;
    };
}

#endif