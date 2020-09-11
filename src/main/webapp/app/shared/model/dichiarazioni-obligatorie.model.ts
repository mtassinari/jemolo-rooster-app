export interface IDichiarazioniObligatorie {
  id?: number;
  stato?: boolean;
  anagraficaId?: number;
  dichiarazioniId?: number;
}

export class DichiarazioniObligatorie implements IDichiarazioniObligatorie {
  constructor(public id?: number, public stato?: boolean, public anagraficaId?: number, public dichiarazioniId?: number) {
    this.stato = this.stato || false;
  }
}
