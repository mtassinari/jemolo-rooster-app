export interface ICompetenzeLng {
  id?: number;
  livello?: number;
  linguaId?: number;
  anagraficaId?: number;
}

export class CompetenzeLng implements ICompetenzeLng {
  constructor(public id?: number, public livello?: number, public linguaId?: number, public anagraficaId?: number) {}
}
