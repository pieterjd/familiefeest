export class Payment {
  date: string;
  amount: number;

  constructor(){
    this.date = new Date().toISOString().split('T')[0];
    this.amount = 0;
  }
}
