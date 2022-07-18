import styled, { css } from "styled-components";
import { shade } from "polished";

interface IContainerProps {
  playerRound: "x" | "o" | "";
}

export const Container = styled.div<IContainerProps>`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 283px;
  height: 44px;
  background-color: transparent;

  button {
    display: flex;
    align-items: center;
    justify-content: center;

    width: 283px;
    height: 44px;
    background-color: #9b0606;
    color: #ffffff;
    border-radius: 30px;
    font-size: 3.2rem;
    border: solid 1px #6f6f6f;

    ${(props) =>
      props.playerRound === "o" &&
      css`
        color: #9b0606;
      `}

    ${(props) =>
      props.playerRound === "x" &&
      css`
        color: #002dcc;
      `}

    &:hover {
      background: ${shade(0.2, "#ff9000")};
    }
  }
`;
