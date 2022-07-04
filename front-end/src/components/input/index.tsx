import React, { InputHTMLAttributes, useEffect, useRef } from "react";
import { useField } from "@unform/core";
import { IconBaseProps } from "react-icons";
import { Container } from "./style";

interface IInputProps extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
  icon?: React.ComponentType<IconBaseProps>;
}

const Input = ({ name, icon: Icon, ...rest }: IInputProps) => {
  const inputRef = useRef<HTMLInputElement>(null);

  const { fieldName, defaultValue, error, registerField } = useField(name);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: inputRef.current,
      path: "value",
    });
  }, [fieldName, registerField]);

  return (
    <Container>
      <input ref={inputRef} name={name} defaultValue={defaultValue} {...rest} />
      {Icon && <Icon size={36} />}
    </Container>
  );
};

export default Input;
