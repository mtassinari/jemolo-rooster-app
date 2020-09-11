import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { AmbitoCompetenzaComponent } from './ambito-competenza.component';
import { AmbitoCompetenzaDetailComponent } from './ambito-competenza-detail.component';
import { AmbitoCompetenzaUpdateComponent } from './ambito-competenza-update.component';
import { AmbitoCompetenzaDeleteDialogComponent } from './ambito-competenza-delete-dialog.component';
import { ambitoCompetenzaRoute } from './ambito-competenza.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(ambitoCompetenzaRoute)],
  declarations: [
    AmbitoCompetenzaComponent,
    AmbitoCompetenzaDetailComponent,
    AmbitoCompetenzaUpdateComponent,
    AmbitoCompetenzaDeleteDialogComponent,
  ],
  entryComponents: [AmbitoCompetenzaDeleteDialogComponent],
})
export class JemoloRoosterAppAmbitoCompetenzaModule {}
