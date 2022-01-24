//
// Created by sheng on 11/3/2021.
//
#include <SFML/Graphics/CircleShape.hpp>
#include <SFML/Window/Event.hpp>

#include "../../include/Game.hpp"
#include "../../include/DifficultyMenu.hpp"
#include "../../include/Menu.hpp"
#include "../../include/GamePanel.hpp"

#include <iostream>

using namespace Engine;

DifficultyMenu::DifficultyMenu(std::shared_ptr<Context> &context) : context(context), normalPressed(false), hardPressed(false), backPressed(false) {

}

void DifficultyMenu::init() {
    context->assets->addFont(MAIN_FONT, "../assets/fonts/Helvetica.ttf");
    context->assets->addTexture(MAMBA, "../assets/images/mamba.png", true);
    context->assets->addTexture(KING_COBRA, "../assets/images/king_cobra.png", true);
    context->assets->addTexture(EASY_BUTTON, "../assets/images/easybutton.png", true);
    context->assets->addTexture(HARD_BUTTON, "../assets/images/hardbutton.png", true);
    context->assets->addTexture(BACK_BUTTON, "../assets/images/back_button.png", true);

    // snake image
    snake.setTexture(context->assets->getTexture(MAMBA));
    snake.setPosition(385, 100);

    //cobras
    cobra1.setTexture(context->assets->getTexture(KING_COBRA));
    cobra1.setScale(0.2,0.2);
    cobra1.setPosition(190,100);

    cobra2.setTexture(context->assets->getTexture(KING_COBRA));
    cobra2.setScale(0.2,0.2);
    cobra2.setPosition(810,99);

    //Normal button
    easyButton.setTexture(context->assets->getTexture(EASY_BUTTON));
    easyButton.setPosition(360,400);

    //Hard button
    hardButton.setTexture(context->assets->getTexture(HARD_BUTTON));
    hardButton.setPosition(580,400);

    //Back button
    backButton.setTexture(context->assets->getTexture(BACK_BUTTON));
    backButton.setPosition(480,550);
//    // Back Button
//    startButton.setFont(context->assets->getFont(MAIN_FONT));
//    startButton.setString("Hard!");
//    startButton.setOrigin(backButton.getLocalBounds().width / 2, backButton.getLocalBounds().height / 2);
//    startButton.setPosition(context->window->getSize().x / 2, context->window->getSize().y / 2 + 200.f);

}

void DifficultyMenu::processInput() {
    // Keyboard menu
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
                    // normal
                    if (event.mouseButton.x >= 360 and event.mouseButton.x <= 560) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 485)
                            normalPressed = true;
                    }
                    // hard
                    if (event.mouseButton.x >= 580 and event.mouseButton.x <= 780) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 480)
                            hardPressed = true;
                    }
                    // back
                    if (event.mouseButton.x >= 480 and event.mouseButton.x <= 655) {
                        if (event.mouseButton.y >= 550 and event.mouseButton.y <= 618)
                            backPressed = true;
                    }
                }
                break;
        }
    }
}


void DifficultyMenu::update(sf::Time) {
    if (normalPressed) {
        GamePanel::difficulty = NORMAL;
        context->states->addState(std::make_unique<GamePanel>(context),true);
    }
    else if (hardPressed) {
        GamePanel::difficulty = HARD;
        context->states->addState(std::make_unique<GamePanel>(context),true);
    }
    else if (backPressed){
        context->states->addState(std::make_unique<Menu>(context),true);
    }
}

void DifficultyMenu::draw() {
    context->window->clear();
    context->window->draw(snake);
    context->window->draw(cobra1);
    context->window->draw(cobra2);
    context->window->draw(gameTitle);
    context->window->draw(easyButton);
    context->window->draw(hardButton);
    context->window->draw(backButton);
    context->window->display();
}
