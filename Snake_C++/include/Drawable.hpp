#ifndef BLACKMAMBA_DRAWABLE_HPP
#define BLACKMAMBA_DRAWABLE_HPP
#pragma once
class Drawable
{
public:
    virtual void draw() = 0;
    virtual int getXPos() = 0;
    virtual void setXPos(int) = 0;
    virtual int getYPos() = 0;
    virtual void setYPos(int) = 0;
};

#endif //BLACKMAMBA_DRAWABLE_HPP
