/* eslint-disable react/no-array-index-key */
import React, { useCallback, useState } from "react";
import Button from "../../components/button";
import { Board, ButtonDisplay, Container, Wrapper } from "./styles";

const Dashboard = () => {
  const [grid, setGrid] = useState<number[]>(Array<number>(9).fill(0));
  const [buttonTexts, setButtonTexts] = useState<string[]>(
    Array<string>(9).fill("")
  );

  const click = useCallback(
    (index: number) => {
      setButtonTexts([
        ...buttonTexts.slice(0, index),
        "X",
        ...buttonTexts.slice(index + 1, buttonTexts.length),
      ]);
    },
    [buttonTexts, setButtonTexts]
  );
  return (
    <Wrapper>
      <h1>WINS: 0 / LOSE: 0</h1>
      <Container>
        <Board>
          {grid.map((card, index) => (
            <Button
              key={index}
              buttonType="button"
              label={buttonTexts[index]}
              onClick={() => {
                click(index);
              }}
            />
          ))}
        </Board>
        <ButtonDisplay>
          <Button buttonType="button" label="restart" />
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
