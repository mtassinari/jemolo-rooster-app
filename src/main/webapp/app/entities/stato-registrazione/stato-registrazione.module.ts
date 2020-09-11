import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { StatoRegistrazioneComponent } from './stato-registrazione.component';
import { StatoRegistrazioneDetailComponent } from './stato-registrazione-detail.component';
import { StatoRegistrazioneUpdateComponent } from './stato-registrazione-update.component';
import { StatoRegistrazioneDeleteDialogComponent } from './stato-registrazione-delete-dialog.component';
import { statoRegistrazioneRoute } from './stato-registrazione.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(statoRegistrazioneRoute)],
  declarations: [
    StatoRegistrazioneComponent,
    StatoRegistrazioneDetailComponent,
    StatoRegistrazioneUpdateComponent,
    StatoRegistrazioneDeleteDialogComponent,
  ],
  entryComponents: [StatoRegistrazioneDeleteDialogComponent],
})
export class JemoloRoosterAppStatoRegistrazioneModule {}
