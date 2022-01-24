#ifndef BLACKMAMBA_HIGHSCORE_HPP
#define BLACKMAMBA_HIGHSCORE_HPP

#include <string>

namespace Engine
{
    class HighScore
    {
    private:
        static const std::string FILE_PATH;
        static void fileWrite(std::string);
    public:
        static void checkScore(int, int);
        static std::string getHighScore();
    };
}

#endif //BLACKMAMBA_HIGHSCORE_HPP
