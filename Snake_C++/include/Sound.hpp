//
// Created by mynam on 28-Mar-21.
//

#ifndef BLACKMAMBA_SOUND_HPP
#define BLACKMAMBA_SOUND_HPP

namespace Engine
{
    class Sound
    {
    protected:
        bool checker = true;
    public:
        void setPlay(bool);

        void playBGM();

        void stopBGM();

        void playBadFood();

        void playGoodFood();

        void playGameOver();
    };
}


#endif //BLACKMAMBA_SOUND_HPP
