cmake_minimum_required(VERSION 3.17)
project(BlackMamba)

set(CMAKE_CXX_STANDARD 14)


add_executable(BlackMamba src/Engine/Game.cpp include/Game.hpp include/Settings.hpp
        src/Engine/GamePanel.cpp include/GamePanel.hpp include/State.hpp src/Engine/StateManager.cpp
        include/StateManager.hpp src/Engine/AssetManager.cpp
        include/AssetManager.hpp src/Engine/Food.cpp include/Food.hpp src/Engine/Player.cpp
        include/Player.hpp src/Math/GameMath.cpp include/GameMath.hpp src/Engine/Snake.cpp
        include/Snake.hpp src/Engine/Body.cpp include/Body.hpp src/Math/SnakeMath.cpp
        include/SnakeMath.hpp src/Engine/Poison.cpp include/Poison.hpp
        include/Drawable.hpp src/Engine/Menu.cpp include/Menu.hpp
        include/Drawable.hpp src/Engine/Menu.cpp include/Menu.hpp
        src/Engine/Help.cpp include/Help.hpp src/Engine/HighScore.cpp include/HighScore.hpp
        src/Engine/DifficultyMenu.cpp include/DifficultyMenu.hpp src/Engine/Pause.cpp include/Pause.hpp src/Engine/Sound.cpp include/Sound.hpp)





if (APPLE)
    include_directories(/usr/local/include)
    include_directories(..\\lib)
else()
    set(SFML_STATIC_LIBRARIES TRUE)
    set(SFML_DIR "C:\\SFML\\lib\\cmake\\SFML")
    include_directories(headers /usr/include c:/SFML/include)
    include_directories(..\\lib)

endif()

find_package(SFML 2.5 COMPONENTS system window graphics audio network audio  REQUIRED)
include_directories(${SFML_INCLUDE_DIRS})
link_directories(..\\lib)

target_link_libraries(BlackMamba sfml-system sfml-window sfml-graphics sfml-audio sfml-network winMM.Lib)



