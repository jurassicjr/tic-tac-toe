import React, { useCallback } from "react";
import { Form } from "@unform/web";
import { FiUser } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { MainContent } from "./styles";
import Input from "../../components/input";
import Button from "../../components/button";

import logo from "../../assets/tic-tac-toe.png";

const Home = () => {
  const navigate = useNavigate();

  const navigateToTicTacToe = useCallback(() => {
    navigate("/dashboard");
  }, []);

  return (
    <MainContent>
      <Form onSubmit={navigateToTicTacToe}>
        <img src={logo} alt="Logo fofinho" />
        <Input name="username" icon={FiUser} placeholder="Insira seu nome" />
        <Button buttonType="submit" label="START" />
        <small>
          Powered By <code>PRESS START</code>
        </small>
      </Form>
    </MainContent>
  );
};

export default Home;
