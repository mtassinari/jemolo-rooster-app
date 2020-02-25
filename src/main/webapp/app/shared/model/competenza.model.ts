export interface ICompetenza {
  id?: number;
  anni?: number;
  anagraficaId?: number;
  ambitoCompId?: number;
}

export class Competenza implements ICompetenza {
  constructor(public id?: number, public anni?: number, public anagraficaId?: number, public ambitoCompId?: number) {}
}
