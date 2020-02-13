import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneErroreComponent } from './registrazione-errore.component';

describe('RegistrazioneErroreComponent', () => {
  let component: RegistrazioneErroreComponent;
  let fixture: ComponentFixture<RegistrazioneErroreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrazioneErroreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrazioneErroreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
