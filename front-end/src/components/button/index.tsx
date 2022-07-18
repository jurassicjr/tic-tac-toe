import React, { ButtonHTMLAttributes } from "react";
import { Container } from "./styles";

interface IButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  buttonType: "submit" | "reset" | "button";
  playerRound?: "x" | "o" | "";
  label: string;
}
const Button = ({
  buttonType,
  label,
  playerRound = "",
  ...rest
}: IButtonProps) => {
  return (
    <Container playerRound={playerRound}>
      <button type={buttonType} {...rest}>
        {label}
      </button>
    </Container>
  );
};

export default Button;
