import styled from "styled-components";

export const MainContent = styled.main`
  display: flex;
  align-items: center;
  justify-content: center;

  width: 100%;
  height: 100vh;

  padding: 3.2rem 0;

  img {
    width: 27.5rem;
    height: 24rem;
    margin-bottom: 3.2rem;
  }

  form {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 600px;
    height: 500px;

    background-color: #ebeaea;
    border-radius: 45px;
    padding: 36px;

    small {
      margin-top: auto;
      font-size: 1.6rem;

      code {
        color: #9b0606;
        font-weight: bold;
      }
    }
  }
`;
