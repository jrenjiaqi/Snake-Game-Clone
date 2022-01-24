#include <fstream>
#include "../../include/HighScore.hpp"

using namespace Engine;

const std::string HighScore::FILE_PATH = "highscore.txt";

void HighScore::checkScore(int p1Score, int p2Score)
{
    // int to store the high score.
    int currentHighScore = std::stoi(getHighScore());
    // P1 beaten high score
    if (p1Score > currentHighScore) fileWrite(std::to_string(p1Score));
    // P2 beaten high score
    else if (p2Score > currentHighScore) fileWrite(std::to_string(p2Score));
}

void HighScore::fileWrite(std::string highScore)
{
    // int to store the high score
    int hs;
    // write initial score to highscore.txt
    std::ofstream outfile(FILE_PATH);

    // read highscore.txt
    std::ifstream myFile(FILE_PATH);

    // if file is empty then write 0 to the highscore.txt
    if (myFile.peek() == std::ifstream::traits_type::eof())
    {
        outfile << "0";
        outfile.close();
    }

    // get the int from highscore.txt
    myFile >> hs;
    myFile.close();

    // overwrite the current score with new high score.
    std::ofstream hscore(FILE_PATH, std::ofstream::trunc);
    // update high score.
    hscore << highScore;
    hscore.close();
}

std::string HighScore::getHighScore()
{
    int line = 0;
    std::ifstream myfile;
    myfile.open(FILE_PATH);
    if (myfile.is_open())
    {
        myfile >> line;
        myfile.close();
    }
    return std::to_string(line);
}