//
// Created by mynam on 28-Mar-21.
//

#include "../../include/Sound.hpp"

#include <iostream>
#include <fstream>
#include <windows.h>
#include <mmsystem.h>
#include <conio.h>


using namespace std;
using namespace Engine;

void Sound::setPlay(bool flag)
{
    this->checker = flag;
}

void Sound::playBGM()
{
    if (checker == true)
    {
        mciSendString("open \"..\\assets\\sounds\\bgm.wav\" type mpegvideo alias bgm", NULL, 0, NULL);
        mciSendString("play bgm repeat", NULL, 0, NULL);
    }
    else
    {
        mciSendString("stop bgm", NULL, 0, NULL);
    }

    /*char goodFood[] = {"..\\assets\\sounds\\bgm.wav"};
    string goodFood_str;
    fstream fp;
    fp.open(goodFood, ios::in);
    goodFood_str = goodFood;
    PlaySound(goodFood_str.c_str(), NULL, SND_FILENAME | SND_ASYNC);
    fp.close();*/


}

void Sound::stopBGM()
{
    //PlaySound(NULL,0,0);
    if (checker == false)
    {
        mciSendString("stop bgm", NULL, 0, NULL);
        mciSendString("close bgm", NULL, 0, NULL);
    }
}

void Sound::playGoodFood()
{
    char goodFood[] = {"..\\assets\\sounds\\goodFood.wav"};
    string goodFood_str;
    fstream fp;
    fp.open(goodFood, ios::in);
    goodFood_str = goodFood;
    PlaySound(goodFood_str.c_str(), NULL, SND_FILENAME | SND_ASYNC);
    fp.close();

    //PlaySound("..\\assets\\sounds\\goodFood.wav", GetModuleHandle(NULL), SND_FILENAME | SND_ASYNC | SND_LOOP);


}

void Sound::playBadFood()
{
    char goodFood[] = {"..\\assets\\sounds\\badFood.wav"};
    string goodFood_str;
    fstream fp;
    fp.open(goodFood, ios::in);
    goodFood_str = goodFood;
    PlaySound(goodFood_str.c_str(), NULL, SND_FILENAME | SND_ASYNC);
    fp.close();

}

void Sound::playGameOver()
{
    char go[] = {"..\\assets\\sounds\\gameover.wav"};
    string go_str;
    fstream fp;
    fp.open(go, ios::in);
    go_str = go;
    PlaySound(go_str.c_str(), NULL, SND_FILENAME | SND_ASYNC);
    fp.close();
}

