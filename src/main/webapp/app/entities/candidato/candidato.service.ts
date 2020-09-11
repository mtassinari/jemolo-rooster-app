import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICandidato } from 'app/shared/model/candidato.model';

type EntityResponseType = HttpResponse<ICandidato>;
type EntityArrayResponseType = HttpResponse<ICandidato[]>;

@Injectable({ providedIn: 'root' })
export class CandidatoService {
  public resourceUrl = SERVER_API_URL + 'api/candidatoes';

  constructor(protected http: HttpClient) {}

  create(candidato: ICandidato): Observable<EntityResponseType> {
    return this.http.post<ICandidato>(this.resourceUrl, candidato, { observe: 'response' });
  }

  update(candidato: ICandidato): Observable<EntityResponseType> {
    return this.http.put<ICandidato>(this.resourceUrl, candidato, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICandidato>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICandidato[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
