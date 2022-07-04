import styled from "styled-components";

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  width: 100%;
  height: 100vh;

  padding: 3.2rem 0;

  h1 {
    font-size: 3.2rem;
    font-weight: bold;
    color: #ffffff;
    margin-bottom: 2.4rem;
  }
`;

export const Container = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 76.5rem;
  height: 63.8rem;

  background-color: #ebeaea;
  border-radius: 4.5rem;
  padding: 3.6rem;

  small {
    margin-top: auto;
    font-size: 1.6rem;

    code {
      color: #9b0606;
      font-weight: bold;
    }
  }
`;

export const Board = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  height: 39.4rem;
  width: 39.4rem;
  margin-top: 6.8rem;

  div {
    height: 12rem;
    width: 12rem;

    button {
      background-color: #d9d9d9;
      height: 12rem;
      width: 12rem;
      border-radius: 0;
      border: none;
    }

    box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.25);
  }
`;

export const ButtonDisplay = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  margin-top: 4.8rem;
  button {
    width: 28.3rem;
  }
`;
