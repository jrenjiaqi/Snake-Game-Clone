#include "../../include/AssetManager.hpp"

using namespace Engine;

/**
 * Associate texture to id
 * @param id: ID to associate file with
 * @param filePath: Path of file
 * @param wantRepeated: Flag to indicate if repeat is needed
 */
void AssetManager::addTexture(int id, const std::string &filePath, bool wantRepeated)
{
    auto texture = std::make_unique<sf::Texture>();

    if(texture->loadFromFile(filePath))
    {
        texture->setRepeated(wantRepeated);
        textures[id] = std::move(texture);
    }
}

/**
 * Associate font to id
 * @param id: ID to associate file with
 * @param filePath: Path of file
 */
void AssetManager::addFont(int id, const std::string &filePath)
{
    auto font = std::make_unique<sf::Font>();

    if(font->loadFromFile(filePath))
    {
        fonts[id] = std::move(font);
    }
}

/**
 * Get texture based on id
 * @param id: ID of texture
 * @return: texture object
 */
const sf::Texture &AssetManager::getTexture(int id) const
{
    return *(textures.at(id).get());
}

/**
 * Get font based on id
 * @param id: ID of font
 * @return font object
 */
const sf::Font &AssetManager::getFont(int id) const
{
    return *(fonts.at(id).get());
}