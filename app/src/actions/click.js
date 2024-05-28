import { useState } from 'react';
import ClickService from '../services/click.service';

export const useCounter = (initialValue: number) => {
  const [count, setCount] = useState(initialValue);

//  const increase = () => setCount(count + 1);
//  const decrease = () => setCount(count - 1);

  const handle = () => {
    var count = ClickService.getCount();
    console.log(count);
    setCount(count);
  };

  return {count, handle };
}