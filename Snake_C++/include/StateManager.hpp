#ifndef BLACKMAMBA_STATEMANAGER_HPP
#define BLACKMAMBA_STATEMANAGER_HPP
#pragma once
#include <memory>
#include <stack>
#include "State.hpp"

namespace Engine
{
    class StateManager
    {
    private:
        std::stack<std::unique_ptr<State>> stateStack;
        std::unique_ptr<State> newState;

        bool add;
        bool replace;
        bool remove;
    public:
        StateManager();

        void addState(std::unique_ptr<State> toAdd, bool replace = false);
        void popCurrent();
        void processStateChange();
        std::unique_ptr<State>& GetCurrent();
    };
}


#endif //BLACKMAMBA_STATEMANAGER_HPP
