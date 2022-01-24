#ifndef BLACKMAMBA_SETTINGS_HPP
#define BLACKMAMBA_SETTINGS_HPP
#pragma once
namespace Engine
{
    class Settings
    {
    public:
        static const int UNIT_SIZE = 24;
        static const int WINDOW_WIDTH = UNIT_SIZE * 48;
        static const int WINDOW_HEIGHT = UNIT_SIZE * 28;
        static const int GAME_HEIGHT = WINDOW_HEIGHT - 3 * UNIT_SIZE;
        static const int CENTER = WINDOW_WIDTH / 2;
        static const int GAME_YPOS = WINDOW_HEIGHT - GAME_HEIGHT;
        static const int POISON_COUNT = 3;
        constexpr static const float SPEED = 1.f / 60.f;  // 60fps
    };
}

#endif //BLACKMAMBA_SETTINGS_HPP
