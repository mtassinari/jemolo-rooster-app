export interface ICurriculum {
  id?: number;
  cv?: string;
  size?: number;
  urlAllegato?: string;
  mimeType?: string;
  note?: string;
  attachId?: number;
  anagraficaId?: number;
}

export class Curriculum implements ICurriculum {
  constructor(
    public id?: number,
    public cv?: string,
    public size?: number,
    public urlAllegato?: string,
    public mimeType?: string,
    public note?: string,
    public attachId?: number,
    public anagraficaId?: number
  ) {}
}
