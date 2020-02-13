import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrazioneDoubleOptinComponent } from './registrazione-double-optin.component';

describe('RegistrazioneDoubleOptinComponent', () => {
  let component: RegistrazioneDoubleOptinComponent;
  let fixture: ComponentFixture<RegistrazioneDoubleOptinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrazioneDoubleOptinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrazioneDoubleOptinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
