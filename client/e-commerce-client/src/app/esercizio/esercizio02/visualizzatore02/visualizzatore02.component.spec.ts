import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Visualizzatore02Component } from './visualizzatore02.component';

describe('Visualizzatore02Component', () => {
  let component: Visualizzatore02Component;
  let fixture: ComponentFixture<Visualizzatore02Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Visualizzatore02Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Visualizzatore02Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
