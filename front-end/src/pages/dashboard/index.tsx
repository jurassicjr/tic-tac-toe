/* eslint-disable react/no-array-index-key */
import React, { useCallback, useState } from "react";
import Button from "../../components/button";
import { Board, ButtonDisplay, Container, Wrapper } from "./styles";

const Dashboard = () => {
  const [grid, setGrid] = useState<Array<"x" | "o" | "">>(
    Array<"x" | "o" | "">(9).fill("")
  );

  const [isGameFinished, setIsGameFinished] = useState<boolean>(false);

  const [isXRound, setIsXRound] = useState<boolean>(
    Math.floor(Math.random() * 2) === 1
  );

  const verifyGameStatus = useCallback(
    (index: number) => {
      if (index === 4) {
        if (
          (grid[index] === grid[5] && grid[index] === grid[3]) ||
          (grid[index] === grid[1] && grid[index] === grid[7]) ||
          (grid[index] === grid[0] && grid[index] === grid[8]) ||
          (grid[index] === grid[2] && grid[index] === grid[6])
        )
          setIsGameFinished(true);
      } else if (index / 2 === 0.0) {
      }
    },
    [setIsGameFinished]
  );

  const click = useCallback(
    (index: number) => {
      if (grid[index] === "") {
        setGrid([
          ...grid.slice(0, index),
          isXRound ? "x" : "o",
          ...grid.slice(index + 1, grid.length),
        ]);
      }
      verifyGameStatus();
      if (isGameFinished) {
        alert(`JOGO ACABOU O GANHADOR FOI: ${isXRound}` ? "X" : "O");
      }
      setIsXRound(!isXRound);
    },
    [grid, setGrid, setIsXRound, isXRound]
  );

  const restart = useCallback(() => {
    setGrid(Array<"x" | "o" | "">(9).fill(""));
    setIsGameFinished(false);
  }, [grid, setGrid, setIsGameFinished]);
  return (
    <Wrapper>
      <h1>WINS: 0 / LOSE: 0</h1>
      <Container>
        <Board>
          {grid.map((card, index) => (
            <Button
              key={index}
              playerRound={grid[index]}
              buttonType="button"
              label={grid[index]}
              onClick={() => {
                click(index);
              }}
            />
          ))}
        </Board>
        <ButtonDisplay>
          <Button buttonType="button" onClick={restart} label="restart" />
          <Button buttonType="button" label="quit" />
        </ButtonDisplay>
        <small>
          Powered By <code>PRESS START</code>
        </small>
      </Container>
    </Wrapper>
  );
};

export default Dashboard;
