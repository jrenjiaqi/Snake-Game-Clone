#ifndef BLACKMAMBA_ASSETMANAGER_HPP
#define BLACKMAMBA_ASSETMANAGER_HPP
#pragma once

#include <map>
#include <memory>
#include <string>

#include <SFML/Graphics/Texture.hpp>
#include <SFML/Graphics/Font.hpp>

namespace Engine
{
    class AssetManager
    {
    private:
        std::map<int, std::unique_ptr<sf::Texture>> textures;
        std::map<int, std::unique_ptr<sf::Font>> fonts;
    public:
        void addTexture(int, const std::string &, bool  = false);
        void addFont(int, const std::string &);

        const sf::Texture &getTexture(int) const;
        const sf::Font &getFont(int) const;
    };
}


#endif //BLACKMAMBA_ASSETMANAGER_HPP
