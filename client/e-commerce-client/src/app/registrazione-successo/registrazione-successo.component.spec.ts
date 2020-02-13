import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneSuccessoComponent } from './registrazione-successo.component';

describe('RegistrazioneSuccessoComponent', () => {
  let component: RegistrazioneSuccessoComponent;
  let fixture: ComponentFixture<RegistrazioneSuccessoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrazioneSuccessoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrazioneSuccessoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
