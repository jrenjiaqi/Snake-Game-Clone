//
// Created by sheng on 11/3/2021.
//
#include <SFML/Graphics/CircleShape.hpp>
#include <SFML/Window/Event.hpp>

#include "../../include/Game.hpp"
#include "../../include/Help.hpp"
#include "../../include/Menu.hpp"
#include "../../include/GamePanel.hpp"

#include <iostream>

using namespace Engine;

Help::Help(std::shared_ptr<Context> &context) : context(context), startButtonPressed(false) {

}

Help::~Help() {

}

void Help::init() {
    context->assets->addTexture(BACKGROUND, "../assets/images/help.png", true);
    context->assets->addFont(MAIN_FONT, "../assets/fonts/Helvetica.ttf");
    background.setTexture(context->assets->getTexture(BACKGROUND));
    background.setPosition(-30, 0);
//    //title
//    gameTitle.setFont(context->assets->getFont(MAIN_FONT));
//    gameTitle.setString("Help");
//    gameTitle.setOrigin(gameTitle.getLocalBounds().width / 2, gameTitle.getLocalBounds().height / 2);
//    gameTitle.setPosition(context->window->getSize().x / 2, context->window->getSize().y / 2 - 200.f);

    // Back Button
    context->assets->addTexture(BACK_BUTTON, "../assets/images/back_button.png",true);
    backButton.setTexture(context->assets->getTexture(BACK_BUTTON));
    backButton.setOrigin(backButton.getLocalBounds().width / 2, backButton.getLocalBounds().height / 2);
    backButton.setPosition(context->window->getSize().x / 2, context->window->getSize().y / 2 + 230.f);

}

void Help::processInput() {
    sf::Event event;
    while (context->window->pollEvent(event)) {
        switch (event.type) {
            case sf::Event::Closed:
                context->window->close();
                break;
            case sf::Event::MouseMoved:
                std::cout << "X: " << event.mouseMove.x << "Y: " << event.mouseMove.y << std::endl;
                break;
            case sf::Event::MouseButtonPressed:
                if (event.mouseButton.button == sf::Mouse::Left) {
                    // back
                    if (event.mouseButton.x >= 495 and event.mouseButton.x <= 650) {
                        if (event.mouseButton.y >= 536 and event.mouseButton.y <= 591)
                                startButtonPressed = true;
                    }
                }
                break;
            case sf::Event::KeyReleased:
                switch (event.key.code) {
                    case sf::Keyboard::Return: {
                        startButtonPressed = true;
                        break;
                    }
                }
                break;
        }
    }
}


void Help::update(sf::Time) {
    if (startButtonPressed) {
        std:: cout << "Go Back!" << std:: endl;
        context->states->addState(std::make_unique<Menu>(context),true);
    }
}

void Help::draw() {
    context->window->clear();
    context->window->draw(background);
    context->window->draw(backButton);
    context->window->display();
}
