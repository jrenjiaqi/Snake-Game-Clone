//
// Created by sheng on 11/3/2021.
// updated by Danial on 17/3/2021.
//
#include <SFML/Graphics/CircleShape.hpp>
#include <SFML/Window/Event.hpp>
#include <SFML/Graphics.hpp>
#include "../../include/Game.hpp"
#include "../../include/Menu.hpp"
#include "../../include/Help.hpp"
#include "../../include/GamePanel.hpp"

#include "../../include/DifficultyMenu.hpp"
#include "../../include/Sound.hpp"


#include <iostream>
#include <windows.h>


using namespace Engine;
Sound bgm;


Menu::Menu(std::shared_ptr<Context> &context)
        : context(context), playButtonPressed(false), exitButtonPressed(false), helpButtonPressed(false),
          onButtonPressed(false), offButtonPressed(false), bgmCheck(true) {

}

Menu::~Menu() {

}


void Menu::init() {
    context->assets->addFont(MAIN_FONT, "../assets/fonts/Helvetica.ttf");
    context->assets->addTexture(MAMBA, "../assets/images/mamba.png", true);
    context->assets->addTexture(KING_COBRA, "../assets/images/king_cobra.png", true);
    context->assets->addTexture(HELP_BUTTON, "../assets/images/help_button.png", true);
    context->assets->addTexture(START_BUTTON, "../assets/images/start_button.png", true);
    context->assets->addTexture(SWITCH_BUTTON, "../assets/images/switch_button.png", true);
    context->assets->addTexture(EXIT_BUTTON, "../assets/images/exit_button.png", true);
    context->assets->addTexture(SOUND, "../assets/images/sound.png", true);

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


    //start button

    start_button.setTexture(context->assets->getTexture(START_BUTTON));
    start_button.setPosition(260,400);

    //help_button
    help_button.setTexture(context->assets->getTexture(HELP_BUTTON));
    help_button.setPosition(480,400);


    //onOFF_button
    switch_button.setTexture(context->assets->getTexture(SWITCH_BUTTON));
    switch_button.setScale(0.5, 0.5);
    switch_button.setPosition(950, 580);

    //exit_button

    exit_button.setTexture(context->assets->getTexture(EXIT_BUTTON));
    exit_button.setPosition(710,400);

    //Sound_Icon

    sound_button.setTexture(context->assets->getTexture(SOUND));
    sound_button.setScale(0.6, 0.6);
    sound_button.setPosition(850, 585);

    //title
    gameTitle.setFont(context->assets->getFont(MAIN_FONT));
    //gameTitle.setString("Black Mamba");
    gameTitle.setOrigin(gameTitle.getLocalBounds().width / 2, gameTitle.getLocalBounds().height / 2);
    gameTitle.setPosition(context->window->getSize().x / 2, context->window->getSize().y / 2 - 200.f);


    bgm.setPlay(bgmCheck);
    bgm.playBGM();



}

void Menu::processInput() {
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
                    // play
                    if (event.mouseButton.x >= 300 and event.mouseButton.x <= 400) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 500)
                            playButtonPressed = true;
                    }
                    // help
                    if (event.mouseButton.x >= 500 and event.mouseButton.x <= 600) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 500)
                            helpButtonPressed = true;
                    }
                    // on
                    if (event.mouseButton.x >= 950 and event.mouseButton.x <= 1035) {
                        if (event.mouseButton.y >= 580 and event.mouseButton.y <= 660) {
                            onButtonPressed = true;
                            bgm.setPlay(true);
                            bgm.playBGM();
                        }

                    }

                    // off
                    if (event.mouseButton.x >= 1045 and event.mouseButton.x <= 1130) {
                        if (event.mouseButton.y >= 580 and event.mouseButton.y <= 660) {
                            offButtonPressed = true;
                            bgm.setPlay(false);
                            bgm.stopBGM();
                        }

                    }

                    // quit
                    if (event.mouseButton.x >= 800 and event.mouseButton.x <= 850) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 500)
                            exitButtonPressed = true;
                    }
                }
                break;
            case sf::Event::KeyReleased:
                switch (event.key.code) {
                    case sf::Keyboard::Up:
                        moveUp();
                        break;
                    case sf::Keyboard::Down:
                        moveDown();
                        break;
                    case sf::Keyboard::Return: {
                        playButtonPressed = false;
                        exitButtonPressed = false;
                        helpButtonPressed = false;
                        onButtonPressed = false;
                        offButtonPressed = false;
                        switch (pressedItem()) {
                            case 0:
                                playButtonPressed = true;
                                std::cout << "Play button pressed" << std::endl;
                                break;

                            case 1:
                                helpButtonPressed = true;
                                std::cout << "Help button pressed" << std::endl;
                                break;
                            case 2:
                                onButtonPressed = true;
                                std::cout << "On button pressed" << std::endl;
                                break;
                            case 3:
                                offButtonPressed = true;
                                std::cout << "Off button pressed" << std::endl;
                                break;
                            case 4:
                                exitButtonPressed = true;
                                std::cout << "Exit button pressed" << std::endl;
                                break;
                        }
                        break;
                    }
                }
                break;
        }
    }
}


void Menu::update(sf::Time) {
//    menu[currentMenuIndex].setFillColor(sf::Color::Yellow);
    if (playButtonPressed) {
        context->states->addState(std::make_unique<DifficultyMenu>(context), true);
    } else if (helpButtonPressed) {
        context->states->addState(std::make_unique<Help>(context), true);
    } else if (exitButtonPressed) {
        context->window->close();
    }
}


void Menu::draw() {
    std::cout << "In draw" << std::endl;
    context->window->clear();
    context->window->draw(snake);
    context->window->draw(cobra1);
    context->window->draw(cobra2);
    context->window->draw(gameTitle);
    context->window->draw(start_button);
    context->window->draw(help_button);
    context->window->draw(switch_button);
    context->window->draw(exit_button);
    context->window->draw(sound_button);
    context->window->draw(menu[0]);
    context->window->draw(menu[1]);
    context->window->draw(menu[2]);
    context->window->display();
}

void Menu::moveUp() {
    if (currentMenuIndex - 1 >= 0) {
        menu[currentMenuIndex].setFillColor(sf::Color::White);
        currentMenuIndex--;
        menu[currentMenuIndex].setFillColor(sf::Color::Yellow);
    }
}

void Menu::moveDown() {
    if (currentMenuIndex + 1 < menuLength) {
        menu[currentMenuIndex].setFillColor(sf::Color::White);
        currentMenuIndex++;
        menu[currentMenuIndex].setFillColor(sf::Color::Yellow);
    }
}