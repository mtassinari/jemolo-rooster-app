export interface IDichiarazioniObligatorie {
  id?: number;
  stato?: boolean;
  dichiarazione?: string;
  anagraficaId?: number;
  dichiarazioniId?: number;
}

export class DichiarazioniObligatorie implements IDichiarazioniObligatorie {
  constructor(
    public id?: number,
    public stato?: boolean,
    public dichiarazione?: string,
    public anagraficaId?: number,
    public dichiarazioniId?: number
  ) {
    this.stato = this.stato || false;
  }
}
