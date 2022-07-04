import styled from "styled-components";

export const Container = styled.div`
  display: flex;

  justify-content: center;
  align-items: center;

  background-color: #ededed;
  border: 0.1px solid #767676;
  box-shadow: 0 0.4rem 0.8rem rgba(28, 37, 44, 0.25);
  border-radius: 3.5rem;
  width: 100%;
  margin-bottom: 1.6rem;

  svg {
    margin-right: 1.6rem;
  }

  input {
    display: flex;
    flex: 1;
    align-items: center;

    border: none;
    height: 4.8rem;
    font-size: 1.6rem;
    background-color: transparent;
    margin-left: 1rem;

    &::placeholder {
      color: #aea8b2;
    }
  }
`;
