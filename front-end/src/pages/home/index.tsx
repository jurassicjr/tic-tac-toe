import React from "react";
import { Form } from "@unform/web";
import { FiUser } from "react-icons/fi";
import { MainContent } from "./styles";
import Input from "../../components/input";
import Button from "../../components/button";

import logo from "../../assets/tic-tac-toe.png";

const Home = () => {
  return (
    <MainContent>
      <Form
        onSubmit={() => {
          console.log("a fazer");
        }}
      >
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
